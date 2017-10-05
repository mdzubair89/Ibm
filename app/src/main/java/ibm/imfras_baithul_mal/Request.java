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
    int requestPhone;
    String requestPerson;
    int requestIbmAmt;
    int requestSepAmt;
    String requestSepList;

    public Request()
    {

    }

    public Request(int requestNo,String requestPurpose,String requestDate,String requestConPerson,String requestPostal,
                   int  requestPhone,String requestPerson,int  requestIbmAmt,int requestSepAmt,String requestSepList)
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

    public int getRequestPhone() {
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
}
