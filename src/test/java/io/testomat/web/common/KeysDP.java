package io.testomat.web.common;

public enum KeysDP {
    ENTER("Enter +1"), TAB("Tab");

    private final String key;

    KeysDP(String key) {
        this.key = key;
    }

    public String toString() {
        return key;
    }


}
