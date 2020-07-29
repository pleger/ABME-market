# ABME-market

An Agent-Based Model (ABM) to simulate the selection of market websites by consumers based on theories of _endorsements_ (Simon & Newell) and _word of mouth_ (Sen & Lerman). For now, we have applied on chinese markets like AliExpress, Wish, BangGood, Alibaba, and BangGood. Altough this project has been applied on market websites, this project is flexible enough to apply others markets.   	   


## Usage
You only should execute the main file ([Main.java](src/Main.java)), which uses the scenario configured [input/SCENARIO.xlsx](input/SCENARIO.xlsx). This Excel file contains a set of sheets that are necessary to fill to corretly execute the software. 






Figure 1. Configurations of markets according to selected attributes.

ABME-market uses the sheet that configures a set of attribute values in different levels. For example, Figure 1 shows six markets with twelve attribute values, where these values are divided in two levels. These values should be obtained from surveys or some an existing database.     






Figure 2. Configurations of buyers according to selected attributes.

ABME-market uses the sheet that configures a set of attribute values. For example, Figure 2 shows the configuration of twelve values.  These values should be obtained from surveys or some an existing database.  






Figure 3. Configuration of participation quotas in markets.    

ABME-market uses the sheet to configure how markets participate. This configuration can be omitted if all markets contains the same quota of participation.  





Figure 4. Global configuration of ABME-market.

Figure 4 shows the global configuration of ABME-market, where many paremeters are configurable. 


## Requirements 
