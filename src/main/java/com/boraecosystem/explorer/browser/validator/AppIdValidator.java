package com.boraecosystem.explorer.browser.validator;

import com.boraecosystem.explorer.browser.property.Channel;
import com.boraecosystem.explorer.browser.service.ChannelService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AppIdValidator implements ConstraintValidator<AppConstraint, String> {
    private final ChannelService channelService;

    public AppIdValidator(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    public void initialize(AppConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final List<Channel> channels = channelService.getChannels();
        for (Channel channel : channels) {
            if (channel.getAppId().equals(value)) return true;
        }

//        throw new NotDefinedAppException();
        return false;
    }

}
