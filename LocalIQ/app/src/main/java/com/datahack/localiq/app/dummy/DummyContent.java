package com.datahack.localiq.app.dummy;

import java.util.*;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Customer> ITEMS = new ArrayList<Customer>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Customer> ITEM_MAP = new HashMap<String, Customer>();

    static {
        // Add 3 sample items.
        addItem(new Customer("0001,jinal dalal,6506699633,1063 Morse Ave Apt 6303 Sunnyvale CA 94089,34,male,Coupa Cafe,,34:bb:26:57:60:e2,34:bb:26:57:60:e1"));
//        addItem(new Customer("0001,jinal dalal,6506699633,1063 Morse Ave Apt 6303 Sunnyvale CA 94089,34,male,Coupa Cafe,,34:bb:26:57:60:e2,34:bb:26:57:60:e1"));
//        addItem(new Customer("0001,jinal dalal,6506699633,1063 Morse Ave Apt 6303 Sunnyvale CA 94089,34,male,Coupa Cafe,,34:bb:26:57:60:e2,34:bb:26:57:60:e1"));
    }

    private static void addItem(Customer item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Customer {
        public String id;
        public String name;
        public String address;
        public String age;
        public String gender;
        public String phoneNumber;
        public String wifi_mac;
        public String bluetooth_uuid;
        public String called_at;
        public String visited_at;

        public Customer() {

        }

        public Customer(String csv) {
            String[] fields = csv.split(",");
            this.id = fields[0];
            this.name = fields[1];
            this.phoneNumber = fields[2];
            this.address = fields[3];
            this.age = fields[4];
            this.gender = fields[5];
            this.wifi_mac = fields[8];
            this.bluetooth_uuid = fields[9];
            this.called_at = "";
            this.visited_at = "";
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
