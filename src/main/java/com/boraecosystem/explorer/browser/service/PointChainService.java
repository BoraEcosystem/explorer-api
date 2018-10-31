package com.boraecosystem.explorer.browser.service;

import com.boraecosystem.explorer.browser.config.Web3Point;
import com.boraecosystem.explorer.browser.exception.InvalidAddressException;
import com.boraecosystem.explorer.browser.model.PointBlockInfo;
import com.boraecosystem.explorer.browser.property.Channel;
import com.boraecosystem.explorer.browser.utils.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthCall;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.web3j.abi.FunctionReturnDecoder.decodeIndexedValue;

@Service
@Slf4j
public class PointChainService {

    private final Web3Point web3Point;

    @Autowired
    public PointChainService(Web3Point web3Point) {
        this.web3Point = web3Point;
    }

    public BigInteger balanceOf(Channel channel, String address) {
        log.error("WalletUtils.isValidAddress(address)::{}, {}", address, WalletUtils.isValidAddress(address));
        if (!AddressUtils.isValidAddress(address)) {
            throw new InvalidAddressException();
        }

        Function function = getFunctionBalanceOf(address);
        String contractAddress = channel.getContractAddress();
        String encode = FunctionEncoder.encode(function);

        EthCall response;
        try {
            response = web3Point.get(channel.getAppId()).ethCall(
                Transaction.createEthCallTransaction(
                    address,
                    contractAddress,
                    encode),
                DefaultBlockParameterName.LATEST)
                .send();

        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }

        log.debug("response:{}", response);
        log.debug("response.getValue():{}", response.getValue());

        // If not exists token address, returns "0x" only
        if ("0x".equals(response.getValue())) {
            throw new RuntimeException();
        }

        Type uint = decodeIndexedValue(response.getValue(), new TypeReference<Uint256>() {
        });

        return new BigInteger(String.valueOf(uint.getValue()));
    }

    public PointBlockInfo getBlockInfoByBlockNumber(Channel channel, BigInteger blockNumber) throws IOException {
        EthBlock.Block block =
            web3Point.get(channel.getAppId()).ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), false)
                .send().getBlock();

        if (block == null) {
            return new PointBlockInfo();
        }

        return PointBlockInfo.builder()
            .blockNo(block.getNumber())
            .blockHash(block.getHash())
            .gasUsed(block.getGasUsed())
            .gasLimit(block.getGasLimit())
            .nonce(block.getNonce())
            .difficulty(block.getDifficulty())
            .totalDifficulty(block.getTotalDifficulty())
            .miner(block.getMiner())
            .mixHash(block.getMixHash())
            .parentHash(block.getParentHash())
            .receiptsRoot(block.getReceiptsRoot())
            .sha3Uncles(block.getSha3Uncles())
            .size(block.getSize())
            .stateRoot(block.getStateRoot())
            .transactionsRoot(block.getTransactionsRoot())
            .transactionCount(block.getTransactions().size())
            .unclesCount(block.getUncles().size())
            .extraData(block.getExtraData())
            .logsBloom(block.getLogsBloom())
            .blockDate(timestampToDateTime(block.getTimestamp()))
            .build();

    }

    private ZonedDateTime timestampToDateTime(BigInteger timestamp) {
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp.longValue()), ZoneId.of("UTC"));
    }


    private Function getFunctionBalanceOf(String address) {
        return new Function("balanceOf",
            Collections.singletonList(new Address(address)), // function의 변수 값
            Collections.singletonList(new TypeReference<Uint256>() {
            }));
    }

    private Function getFunctionNonArg(String functionName) {
        return new Function(functionName, Collections.emptyList(),
            Collections.singletonList(new TypeReference<Uint256>() {
            }));
    }

}
