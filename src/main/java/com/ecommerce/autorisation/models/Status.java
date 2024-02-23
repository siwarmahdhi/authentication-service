package com.ecommerce.autorisation.models;

/**
 * Status de l'entité : actif ou inactif
 */
public enum Status {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    /**
     * Libellé de l'état
     */
    private final String libelle;

    /**
     * Constructeur privé avec libellé.
     *
     * @param libelle libellé.
     */
    Status(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Trouve l'état de l'entité associé à un libellé
     *
     * @param libelle libellé de l'état
     * @return état entité correspondant à un libelle; null sinon.
     */
    public static Status fromStringValue(String libelle) {
        for (Status status : Status.values()) {
            if (status.libelle.equalsIgnoreCase(libelle)) {
                return status;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
