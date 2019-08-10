package Simulation;

import GUI.XChartDriver;
import Log.Logger;

import java.util.ArrayList;

public class Simulation {
    int iterationTime;
    Network network;
    final int maxTime;
    Logger logger;

    public Simulation(double[] probabilitiesBuyer, int maxTime) {
        iterationTime = 0;
        network = NetworkFactory.getNetwork(NetworkFactory.NETWORK_TYPE_1, probabilitiesBuyer);
        XChartDriver.createXChartDriver(network.getBuyersSize());
        network.registerBuyersOnChart();
        this.maxTime = maxTime;
    }

    public void enableLog(ArrayList<String> headers) {
        logger = new Logger(headers);
    }

    public void runSimulation() throws Exception {
        while (iterationTime < maxTime) {
            ArrayList<ArrayList<String>> experiences = network.doStep();
            for (ArrayList<String> record : experiences) {
                logger.addLog(record);
            }
            if(iterationTime==0) XChartDriver.drawChart();
            else XChartDriver.updateChart();
            Thread.sleep(500);
            iterationTime++;
        }
        logger.writeLog();
    }
}
