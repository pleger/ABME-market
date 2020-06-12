package agent;

import endorsement.EndorsementFactory;
import endorsement.Endorsements;
import inputManager.Configuration;
import logger.Console;
import reporter.MarketEvaluationData;
import simulation.Simulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interaction {

    public static Endorsements interact(int period, Buyer buyer, List<Market> markets) {
        Market selectedMarket = selectMarket(period, buyer, markets);
        Console.setAssert(selectedMarket != null, "Interaction: No Market selected. Selected:" + selectedMarket + " marketSize:" + markets.size() + " buyerSize:" + buyer.getID());

        return EndorsementFactory.createByStep(period, buyer, selectedMarket);
    }

    private static Market selectMarket(int period, Buyer buyer, List<Market> markets) {
        Map<Integer, Double> evaluations = new HashMap<>();

        //double[] evaluations = new double[markets.size()];
        for (Market market : markets) {
            Endorsements endors = buyer.getEndorsements().filterByMarket(market).filterByMemory(period);
            double eval = evaluateMarket(endors.toArray());
            //evaluations[market.getID()] = eval;
            evaluations.put(market.getID(), eval);

            reporter.Reporter.addMarketEvaluationData(new MarketEvaluationData(Simulation.ID, period, buyer.getID(), market.getName(), eval));
        }
        
        int idSelected = MarketSelectionStrategies.BY_PROBABILITY(evaluations);
        Market mkSelected = null;

        for (Market mk: markets) {
            if (mk.getID() == idSelected) {
                mkSelected = mk;
                break;
            }
        }
        
        buyer.setCurrentEvaluation(evaluations.get(idSelected));
        return mkSelected;
    }

    private static double evaluateMarket(double[] values) {
        double result = 0;

        for (double value : values) {
            result += value > 0 ? Math.pow(Configuration.BASE, value) : -1 * Math.pow(Configuration.BASE, Math.abs(value));
        }
        return result;
    }
}
