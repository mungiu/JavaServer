package Model;

import org.json.JSONObject;

public class SocketRequest {

    private ACTION action;
    private Object obj;
    private String locationID;
    private String companyID;
    private String palletID;

    public SocketRequest(ACTION action, Object obj, String locationID){
        this.locationID= locationID;
    }

    public SocketRequest(ACTION action, String ID){
        if(action.equals(ACTION.GET_COMPANY_BYID))
            this.companyID = ID;
        else if(action.equals(ACTION.GET_PALLET_BYID))
            this.palletID = ID;
        else if(action.equals(ACTION.GET_LOCATION_BYID))
            this.locationID = ID;
    }

    public SocketRequest(ACTION action, String ID1, String ID2) {
        if(action.equals(ACTION.ASSIGN_LOCATION_TO_COMPANY) || action.equals(ACTION.REMOVE_LOCATION_FROM_CURRENT_COMPANY)){
            this.locationID = ID1;
            this.companyID= ID2;}

        if(action.equals(ACTION.REMOVE_PALLET)){
            this.palletID=ID1;
            this.locationID=ID2;}
    }
    public SocketRequest(String palletID, String companyID) {

    }

    public SocketRequest(ACTION action, Object obj) {
        this.action = action;
        this.obj = obj;
    }


    public enum ACTION
    {
        REGISTER_COMPANY,
        ASSIGN_LOCATION_TO_COMPANY,
        REMOVE_LOCATION_FROM_CURRENT_COMPANY,
        EDIT_COMPANY,
        GET_COMPANY_LIST,
        GET_COMPANY_BYID,
        STORE_PALLET,
        REMOVE_PALLET,
        GET_PALLET_BYID,
        GET_PALLET_LIST,
        GET_AVAILABLE_LOCATIONS,
        GET_LOCATION_BYID
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
}
