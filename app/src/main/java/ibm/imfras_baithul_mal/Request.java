package ibm.imfras_baithul_mal;

/**
 * Created by mohamedzubair on 10/1/2017.
 */

public class Request {

    int requestNo;
    String requestPurpose;
    String requestDate;
    String requestConPerson;
    String requestPostal;
    String requestPhone;
    String requestPerson;
    int requestIbmAmt;
    int requestSepAmt;
    String requestSepList;
    String requestStatus;
    String requestType;
    String requestTreatment = "";
    String requestPatientName = "";
    String requestTreatmentCost = "";

    public  Request()
    {

    }

    public Request(int requestNo,String requestPurpose,String requestDate,String requestConPerson,String requestPostal,
                   String  requestPhone,String requestPerson,int  requestIbmAmt,int requestSepAmt,String requestSepList,
                   String requestStatus, String requestType, String requestTreatment,String requestPatientName,String requestTreatmentCost)
    {
        this.requestNo          = requestNo;
        this.requestPurpose     = requestPurpose;
        this.requestDate        = requestDate;
        this.requestConPerson   = requestConPerson;
        this.requestPostal      = requestPostal;
        this.requestPhone       = requestPhone;;
        this.requestPerson      = requestPerson;
        this.requestIbmAmt      = requestIbmAmt;
        this.requestSepAmt      = requestSepAmt;
        this.requestSepList     = requestSepList;
        this.requestStatus      = requestStatus;
        this.requestType        = requestType;
        this.requestTreatment   = requestTreatment;
        this.requestPatientName = requestPatientName;
        this.requestTreatmentCost = requestTreatmentCost;
    }

    public int getRequestNo() {
        return requestNo;
    }

    public String getRequestPurpose() {
        return requestPurpose;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestConPerson() {
        return requestConPerson;
    }

    public String getRequestPostal() {
        return requestPostal;
    }

    public String getRequestPhone() {
        return requestPhone;
    }

    public String getRequestPerson() {
        return requestPerson;
    }

    public int getRequestIbmAmt() {
        return requestIbmAmt;
    }

    public int getRequestSepAmt() {
        return requestSepAmt;
    }

    public String getRequestSepList() {
        return requestSepList;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestTreatment() {
        return requestTreatment;
    }

    public void setRequestTreatment(String requestTreatment) {
        this.requestTreatment = requestTreatment;
    }

    public String getRequestPatientName() {
        return requestPatientName;
    }

    public void setRequestPatientName(String requestPatientName) {
        this.requestPatientName = requestPatientName;
    }

    public String getRequestTreatmentCost() {
        return requestTreatmentCost;
    }

    public void setRequestTreatmentCost(String requestTreatmentCost) {
        this.requestTreatmentCost = requestTreatmentCost;
    }
}
