package inputManager;

import logger.Console;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private final static int D_PERIODS = 30;
    private final static int D_AGENTS = 10;
    private final static int D_CONTACTS = 17;
    private final static double D_FRIENDS = .7;
    private final static int D_LEVELS = 2; //2 or 3
    private final static int D_REPETITIONS = 0;
    private final static boolean D_GUI = false; //could be removed
    private final static double D_BASE = 1.2;
    private final static int D_MEMORY = -1;    //-1 infinite
    private final static boolean D_SAVED_ENDORSEMENTS = false;
    private final static boolean D_SAVED_DETAILED_RESULTS = false;
    private final static boolean D_MARKET_QUOTA = false;
    private final static boolean D_FRIEND_RECOMMENDATION = false;

    public static String FILE_NAME;
    public static String OUTPUT_DIRECTORY;

    public static int MARKETS;
    public static int ATTRIBUTES_M;
    public static int ATTRIBUTES_B;

    public static int PERIODS = D_PERIODS;
    public static int AGENTS = D_AGENTS;
    public static int CONTACTS = D_CONTACTS;
    public static double FRIENDS = D_FRIENDS;
    public static int LEVELS = D_LEVELS; //2 or 3
    public static int REPETITIONS = D_REPETITIONS;
    public static boolean GUI = D_GUI;
    public static double BASE = D_BASE;
    public static int MEMORY = D_MEMORY;
    public static boolean SAVED_ENDORSEMENTS = D_SAVED_ENDORSEMENTS;
    public static boolean SAVED_DETAILED_RESULTS = D_SAVED_DETAILED_RESULTS;
    public static boolean MARKET_QUOTA = D_MARKET_QUOTA;
    public static boolean FRIEND_RECOMMENDATION = D_FRIEND_RECOMMENDATION;

    public static void set(HashMap<String, Double> conf) {
        checkConfigurationInput(conf);

        PERIODS = conf.get("PERIODS") != null ? conf.get("PERIODS").intValue() : D_PERIODS;
        AGENTS = conf.get("AGENTS") != null ? conf.get("AGENTS").intValue() : D_AGENTS;
        CONTACTS = conf.get("CONTACTS") != null ? conf.get("CONTACTS").intValue() : D_CONTACTS;
        FRIENDS = conf.get("FRIENDS") != null ? conf.get("FRIENDS") : D_FRIENDS;
        LEVELS = conf.get("LEVELS") != null ? conf.get("LEVELS").intValue() : D_LEVELS;
        REPETITIONS = conf.get("REPETITIONS") != null ? conf.get("REPETITIONS").intValue() : D_REPETITIONS;
        GUI = conf.get("GUI") != null ? conf.get("GUI") == 1 : D_GUI;
        BASE = conf.get("BASE") != null ? conf.get("BASE") : D_BASE;
        MEMORY = conf.get("MEMORY") != null ? conf.get("MEMORY").intValue() : D_MEMORY;
        SAVED_ENDORSEMENTS = conf.get("SAVED_ENDORSEMENTS") != null ? conf.get("SAVED_ENDORSEMENTS") == 1 : D_SAVED_ENDORSEMENTS;
        SAVED_DETAILED_RESULTS = conf.get("SAVED_DETAILED_RESULTS") != null ? conf.get("SAVED_DETAILED_RESULTS") == 1 : D_SAVED_DETAILED_RESULTS;
        MARKET_QUOTA = conf.get("MARKET_QUOTA") != null ? conf.get("MARKET_QUOTA") == 1 : D_MARKET_QUOTA;
        FRIEND_RECOMMENDATION = conf.get("FRIEND_RECOMMENDATION") != null ? conf.get("FRIEND_RECOMMENDATION") == 1 : D_FRIEND_RECOMMENDATION;
    }

    public static void setPath(String fileName) {
        FILE_NAME = fileName;
        DateFormat df = new SimpleDateFormat("dd-MM-yy(HH-mm-ss)");
        OUTPUT_DIRECTORY = "output/" + fileName + df.format(new Date());

        //making directory
        try {
            if (new File(OUTPUT_DIRECTORY).mkdir()) {
                Console.info("Directory was created: " + OUTPUT_DIRECTORY);
            } else {
                Console.error("Directory was NOT create: " + OUTPUT_DIRECTORY);
            }
        } catch (SecurityException se) {
            Console.error("Directory cannot be created: " + OUTPUT_DIRECTORY);
            Console.error("ERROR: " + se);
            se.printStackTrace();
            System.exit(1);
        }
    }

    public static void setAttributes(int markets, int buyers) {
        set("ATTRIBUTES_M", markets);
        set("ATTRIBUTES_B", buyers);
    }

    public static void setMarkets(int markets) {
        set("MARKETS", markets);
    }

    private static void set(String name, double value) {
        switch (name.toUpperCase()) {
            case "PERIODS":
                PERIODS = (int) value;
                break;
            case "AGENTS":
                AGENTS = (int) value;
                break;
            case "CONTACTS":
                CONTACTS = (int) value;
                break;
            case "FRIENDS":
                FRIENDS = value;
                break;
            case "ATTRIBUTES_M":
                ATTRIBUTES_M = (int) value;
                break;
            case "ATTRIBUTES_B":
                ATTRIBUTES_B = (int) value;
                break;
            case "MARKETS":
                MARKETS = (int) value;
                break;
            case "REPETITIONS":
                REPETITIONS = (int) value;
                break;
            case "LEVELS":
                LEVELS = (int) value;
                break;
            case "GUI":
                GUI = value == 1;
                break;
            case "BASE":
                BASE = value;
                break;
            case "MEMORY":
                MEMORY = (int) value;
                break;
            case "SAVED_ENDORSEMENT":
                SAVED_ENDORSEMENTS = value == 1;
                break;
            case "SAVED_DETAILED_RESULTS":
                SAVED_DETAILED_RESULTS = value == 1;
                break;
            case "MARKET_QUOTA":
                MARKET_QUOTA = value == 1;
                break;
            case "FRIEND_RECOMMENDATION":
                FRIEND_RECOMMENDATION = value == 1;
                break;
            default:
                Console.error("CONFIGURATOR.SET: Wrong Parameter");
        }
    }

    private static void checkConfigurationInput(HashMap<String, Double> conf) {
        if (conf.get("PERIODS") == null) {
            Console.warn("PERIODS is missing.");
        }
        if (conf.get("AGENTS") == null) {
            Console.warn("AGENTS is missing.");
        }
        if (conf.get("CONTACTS") == null) {
            Console.warn("CONTACTS is missing.");
        }
        if (conf.get("FRIENDS") == null) {
            Console.warn("LEVELS is missing.");
        }
        if (conf.get("LEVELS") == null) {
            Console.warn("LEVELS is missing.");
        }
        if (conf.get("REPETITIONS") == null) {
            Console.warn("REPETITIONS is missing.");
        }
        if (conf.get("GUI") == null) {
            Console.warn("GUI is missing.");
        }
        if (conf.get("BASE") == null) {
            Console.warn("BASE is missing.");
        }
        if (conf.get("MEMORY") == null) {
            Console.warn("MEMORY is missing.");
        }
        if (conf.get("SAVED_ENDORSEMENTS") == null) {
            Console.warn("SAVED_ENDORSEMENTS is missing.");
        }
        if (conf.get("SAVED_DETAILED_RESULTS") == null) {
            Console.warn("SAVED_DETAILED_RESULTS is missing.");
        }
        if (conf.get("MARKET_QUOTA") == null) {
            Console.warn("MARKET_QUOTA is missing.");
        }
        if (conf.get("FRIEND_RECOMMENDATION") == null) {
            Console.warn("FRIEND_RECOMMENDATION is missing.");
        }
    }

    public static Map<String, Double> toMap() {
        Map<String, Double> conf = new HashMap<>();
        conf.put("PERIODS", (double) PERIODS);
        conf.put("AGENTS", (double) AGENTS);
        conf.put("CONTACTS", (double) CONTACTS);
        conf.put("FRIENDS", FRIENDS);
        conf.put("LEVELS", (double) LEVELS);
        conf.put("REPETITIONS", (double) REPETITIONS);
        conf.put("GUI", GUI ? 1.0 : 0.0);
        conf.put("BASE", BASE);
        conf.put("MEMORY", (double) MEMORY);
        conf.put("SAVED_ENDORSEMENTS", SAVED_ENDORSEMENTS ? 1.0 : 0.0);
        conf.put("SAVED_DETAILED_RESULTS", SAVED_DETAILED_RESULTS ? 1.0 : 0.0);
        conf.put("MARKET_QUOTA", MARKET_QUOTA ? 1.0 : 0.0);
        conf.put("FRIEND_RECOMMENDATION", FRIEND_RECOMMENDATION ? 1.0 : 0.0);

        return conf;
    }

    public static String toStringConfiguration() {
        return Configuration.toMap().toString();
    }
}
