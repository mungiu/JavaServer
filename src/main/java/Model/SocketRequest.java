package Model;



import java.io.Serializable;
import java.sql.Date;

public class SocketRequest implements Serializable {

    private ACTION action;
    private Object obj;
    private String locationID;
    private String companyID;
    private String palletID;
    private Date rentalStart;

    /**it instantiates the socket request with three parameters one action, one object and one string.
     *
     * @param action
     * @param obj
     * @param locationID
     * @param companyID
     * @param palletID
     */
    public SocketRequest(ACTION action, Object obj, String locationID, String companyID, String palletID){
        this.action=action;
        this.obj=obj;
        this.locationID= locationID;
        this.companyID=companyID;
        this.palletID=palletID;
    }

    /**the class action which has a list of specific actions to be handled on specific objects
     *
     */
    public enum ACTION
    {
        REGISTER_COMPANY,
        ASSIGN_LOCATION_TO_COMPANY,
        REMOVE_LOCATION_FROM_CURRENT_COMPANY,
        EDIT_COMPANY,
        EDIT_PALLET,
        GET_COMPANY_LIST,
        GET_COMPANY_BYID,
        STORE_PALLET,
        REMOVE_PALLET,
        GET_PALLET_BYID,
        GET_PALLET_LIST,
        GET_AVAILABLE_LOCATIONS,
        GET_LOCATION_BYID,
        GET_LOCATIONS_OF_CURRENT_COMPANY
    }
    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public ACTION getAction() {
        return action;
    }

    public void setAction(ACTION action) {
        this.action = action;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getPalletID() {
        return palletID;
    }

    public void setPalletID(String palletID) {
        this.palletID = palletID;
    }

    public Date getRentalStartDate() {
        return rentalStart;
    }

    public void setRentalStartDate(Date rentalStart) {
        this.rentalStart = rentalStart;
    }
}
