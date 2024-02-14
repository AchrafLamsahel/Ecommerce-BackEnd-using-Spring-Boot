package ma.ecommerce.enumurations;

public enum UserErrorMessage {
    USER_NOT_FOUND("Utilisateur introuvable"),
    INTERNAL_SERVER_ERROR("Erreur serveur"),
    USER_NOT_AUTHORIZED("Non autorisé"),
    EMAIL_ALREADY_EXIST("Adresse mail déjà utilisée"),
    EMAIL_INVALID("Adresse mail invalide ."),
    FIRSTNAME_INVALID("Prénom  invalide . Il doit contenir seulement des caractères."),
    LASTNAME_INVALID("Nom  invalide, Il doit contenir seulement des caractères."),
    PASSWORD_MATCH_ERROR("Le mot de passe entré ne correspond pas au mot de passe de confirmation."),
    PASSWORD_LENGTH_ERROR("Le mot de passe doit contient au moins 8 caractères."),
    PHONE_INVALID("Numéro téléphone invalide."),
    ROLE_ALREADY_EXIST("Ce rôle existe");

    private final String msg;
    UserErrorMessage(String msg){
        this.msg = msg;
    }
    public String getMessage(){
        return this.msg;
    }
}
