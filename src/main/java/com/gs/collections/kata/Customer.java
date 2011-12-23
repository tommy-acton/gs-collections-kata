package com.gs.collections.kata;

import java.util.ArrayList;
import java.util.List;

import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.block.function.AddFunction;
import com.gs.collections.impl.utility.ListIterate;
import org.junit.Assert;

/**
 * Customers have a name, city and a list of {@link Order}s
 */
public class Customer
{
    public static final Function<Customer, String> TO_NAME = new Function<Customer, String>()
    {
        @Override
        public String valueOf(Customer customer)
        {
            Assert.fail();
            return null;
        }
    };

    public static final Function<Customer, String> TO_CITY = null;

    public static final Function<Customer, Double> TO_TOTAL_ORDER_VALUE =
            new Function<Customer, Double>()
            {
                @Override
                public Double valueOf(Customer customer)
                {
                    return customer.getTotalOrderValue();
                }
            };

    private final String name;
    private final String city;

    private final List<Order> orders = new ArrayList<Order>();

    public Customer(String name, String city)
    {
        this.name = name;
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getName()
    {
        return this.name;
    }

    public List<Order> getOrders()
    {
        return this.orders;
    }

    public void addOrder(Order anOrder)
    {
        this.orders.add(anOrder);
    }

    public double getTotalOrderValue()
    {
        MutableList<Double> orderValues = ListIterate.collect(this.orders, new Function<Order, Double>()
        {
            @Override
            public Double valueOf(Order order)
            {
                return order.getValue();
            }
        });
        return orderValues.injectInto(0.0, AddFunction.DOUBLE_TO_DOUBLE);
    }
}
