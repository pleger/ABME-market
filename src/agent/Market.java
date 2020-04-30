package agent;

import endorsement.AttributesMarket;
import inputManager.InnerMarket;
import logger.Console;
import org.jetbrains.annotations.NotNull;

public class Market {
    private static int counter = 0;

    private final int ID;
    private final String name;
    private final AttributesMarket attributes;

    Market(@NotNull InnerMarket innerMarket) {
        this.ID = counter++;
        this.name = innerMarket.name;
        this.attributes = new AttributesMarket(innerMarket.attributeNames, innerMarket.attributeValues);
        Console.trace("Market:"+this);
    }

    public int getID() {
        return ID;
    }

    public String getName(){
        return name;
    }

    public AttributesMarket getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id="+ID+"," +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }

}
