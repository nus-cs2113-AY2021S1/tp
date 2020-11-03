public class Parser {//name week time
    public static Customer parseCustomer(String inputMessage) throws DayOfWeekException, ArriveTimeException {

        String[] inputWords = inputMessage.split("/"); //split the input message
        String customerName = inputWords[0];
        int dayOfWeek = Integer.parseInt(inputWords[1])-1;
        if(dayOfWeek>=7||dayOfWeek<0){
            throw new DayOfWeekException();
        }
        int arriveTime = Integer.parseInt(inputWords[2]);
        if(arriveTime<0||arriveTime>=2400){
            throw new ArriveTimeException();
        }
        return new Customer(customerName,1,arriveTime,dayOfWeek);
    }
}
