package com.test.lab04.aggregators;

import com.test.lab04.datamodel.Property;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

public class PropertyAggregator implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        if (oldExchange == null) {
            List<Property> propertyList = new ArrayList<Property>();

            Property oldProperty = newExchange.getIn().getBody(Property.class);
            propertyList.add(oldProperty);

            newExchange.getIn().setBody(propertyList);

            newExchange.setProperty("totalPrice", oldProperty.getPrice());

            return newExchange;
        } else {
            List<Property> propertyList = oldExchange.getIn().getBody(List.class);

            Property oldProperty = newExchange.getIn().getBody(Property.class);
            propertyList.add(oldProperty);

            Integer price = (Integer) oldExchange.getProperty("totalPrice");
            oldExchange.setProperty("totalPrice", price + oldProperty.getPrice());

            oldExchange.getIn().setBody(propertyList);
            return oldExchange;
        }

    }
}
