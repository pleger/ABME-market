package Agent;

import Endorsement.AttributesBuyer;
import Endorsement.EndorsementFactory;
import Endorsement.Endorsements;
import GUI.DataChart;
import InputManager.Configuration;
import InputManager.InnerBuyer;
import Reporter.Reporter;
import Reporter.IterationData;
import Reporter.EndorsementData;
import Simulation.FlyWeight;
import Simulation.Simulation;
import Simulation.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Buyer implements Step, FlyWeight {
    private static final Logger logger = LogManager.getRootLogger();
    private static int counter = 0;

    private final int ID;
    private final AttributesBuyer attribute;
    private final List<Buyer> friends;
    private final Endorsements endors;
    private List<Market> knownMarkets;

    private final DataChart data;
    private double evaluation;

    Buyer(InnerBuyer ib) {
        this.ID = counter++;
        this.friends = new ArrayList<>();
        this.knownMarkets = new ArrayList<>();
        this.endors = new Endorsements();

        ArrayList<Double[]> values = new ArrayList<>();
        for (Double value : ib.attributeValues) {
            values.add(new Double[]{value});
        }

        attribute = new AttributesBuyer(ib.attributeNames, values);
        data = new DataChart(Integer.toString(ID));

        logger.trace("buyer:" + this);
    }

    public void setFriends(List<Buyer> buyers) {
        int friendCounter = 0;
        int friendSize = (int) (Configuration.CONTACTS * Configuration.FRIENDS);

        while (friendCounter < friendSize) {
            Buyer potentialContact = buyers.get((int) (Math.random() * buyers.size()));
            if (addFriend(potentialContact)) {
                ++friendCounter;
            }
        }
    }

    private boolean addFriend(Buyer potentialContact) {
        if (!friends.contains(potentialContact)) {
            friends.add(potentialContact);
            return true;
        }
        return false;
    }

    public Endorsements getEndorsements() {
        return endors;
    }

    public AttributesBuyer getAttribute() {
        return attribute;
    }

    public int getID() {
        return ID;
    }

    public DataChart getDataSeries() {
        return data;
    }

    public void setInitialEndorsements() {
        endors.clear();
        knownMarkets.iterator().forEachRemaining(market -> endors.addAll(EndorsementFactory.createInitial(-1, this, market)));
    }

    public void setKnowMarkets(List<Market> markets) {
        this.knownMarkets = new ArrayList<>(markets);
    }

    @Override
    public void doStep(int period) {
        endors.addAll(Interaction.interact(period, this, knownMarkets));

        //adding data
        data.addData(period, endors.getSelectedMarket(period).getID());
        Reporter.addIterationData(new IterationData(Simulation.ID, period, getID(), endors.getSelectedMarket(period).getName(), evaluation));
    }

    @Override
    public void reinit() {
        evaluation = 0;
        endors.clear();
        friends.clear();
        knownMarkets.clear();
    }

    @Override
    public String toString() {
        StringBuilder stringValue = new StringBuilder();

        for (int i = 0; i < attribute.size(); ++i) {
            stringValue.append(attribute.getName(i)).append("[").append(attribute.getValue(i)).append("], ");
        }

        return "Buyer{" +
                "ID=" + ID +
                ", attr=" + stringValue +
                '}';
    }

    public void setCurrentEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    public ArrayList<EndorsementData> getEndorsementData() {
        ArrayList<EndorsementData> endorsData = new ArrayList<>();
        endors.forEach(endor -> {
            endorsData.add(new EndorsementData(Simulation.ID, endor.getPeriod(), ID, endor.getMarket().getName(),
                    endor.getAttributeName(), endor.getValue()));
        });

        return endorsData;
    }
}

