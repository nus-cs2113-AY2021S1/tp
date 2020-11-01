public class Parser {//name week time
    public static Customer parseCustomer(String inputMessage) {
        String[] inputWords = inputMessage.split("/"); //split the input message
        String customerName = inputWords[0];
        String dayOfWeek = inputWords[1];
        String arriveTime = inputWords[2];


        return new Customer(customerName,1,Integer.parseInt(arriveTime),Integer.parseInt(dayOfWeek));
    }
}
