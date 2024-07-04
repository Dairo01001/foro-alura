package com.dairodev.api_foro.Topics;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.time.LocalDate;

public class TopicAggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return new RegisterTopicRequest(
                argumentsAccessor.getString(0),
                argumentsAccessor.getString(1),
                LocalDate.parse(argumentsAccessor.getString(2)),
                Boolean.parseBoolean(argumentsAccessor.getString(3))
        );
    }
}
