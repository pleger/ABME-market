# ABME-market

An Agent-Based Model (ABM) to simulate the selection of market websites by consumers based on theories of _endorsements_ (Simon & Newell) and _word of mouth_ (Sen & Lerman). For now, we have applied on chinese markets like AliExpress, Wish, BangGood, Alibaba, and BangGood. Altough this project has been applied on market websites, this project is flexible enough to apply others markets.   	   


## Usage
You only should execute the main file ([Main.java](src/Main.java)), which uses the scenario configured [input/SCENARIO.xlsx](input/SCENARIO.xlsx) and generates an Excel file as [output](output). 

### Input

The input Excel file contains a set of sheets that are necessary to fill in order to corretly execute the software. 


![Figure1](http://pleger.cl/sites/ABME-market/markets.png)


Figure 1. Configurations of markets according to selected attributes.

ABME-market uses the sheet that configures a set of attribute values in different levels. For example, Figure 1 shows six markets with twelve attribute values, where these values are divided in two levels. These values should be obtained from surveys or some an existing database.     



![Figure2](http://pleger.cl/sites/ABME-market/buyers.png)


Figure 2. Configurations of buyers according to selected attributes.

ABME-market uses the sheet that configures a set of attribute values. For example, Figure 2 shows the configuration of twelve values.  These values should be obtained from surveys or some an existing database.  




![Figure3](http://pleger.cl/sites/ABME-market/quota.png)

Figure 3. Configuration of participation quotas in markets.    

ABME-market uses the sheet to configure how markets participate. This configuration can be omitted if all markets contains the same quota of participation.  




![Figure4](http://pleger.cl/sites/ABME-market/configuration.png)

Figure 4. Global configuration of ABME-market.

A global configuration of ABME-market is shown in Figure 4. Many paremeters are configurable, for example, number of agents, periods, repetions of the execution of a simulation. An important configuration is scenario, which a numeric value. This numeric value indicates which previously defined [Scenario.java](src/scenario/Scenario.java) scenario will be used.    

### Output

The output Excel file contains a set of sheets with different of results, which can contain information as any potential decision of an agent up to sells of every markets. 

![Figure5](http://pleger.cl/sites/ABME-market/sells.png)

Figure 5. Unique sells of each market.

In addition, ABME-market can graph any simulation as shown in Figure 6.

![Figure6](http://pleger.cl/sites/ABME-market/simulation.png)

Figure 6. Graph of a simulation in ABME-market.


## Requirements 

This project is implemented on Java (1.8.0_11) under the [IDE Intellij Idea](https://www.jetbrains.com/), which was mainly executed on a MacOS Catalina, 3,1 GHz Dual-Core Intel Core i5 with 8GB of RAM.
