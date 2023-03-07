package factory;

public class AccountFactory {

    public static Account createAccount(){
        return new Account();
    }
    public static Account createAccountJohn(){
        return new Account("john@gmail.com", "john", "lenon", "4128590763", "EB0114114120000004128590763","19945");
    }
    public static Account createAccountPaul(){
        return new Account("paul@gmail.com", "paul", "mccartney", "0257436891","EB6414110250000000257436891", "20000");
    }
    public static Account createAccountGeorge(){
        return new Account("george@gmail.com", "george", "harrison", "0549621378", "EB6214110540000000549621378","40000.00");
    }

    public static Account createAccountRingo(){
        return new Account("ringo@gmail.com", "ringo", "starr", "0157364982", "EB7814110150000000157364982","20055");
    }

   

}
