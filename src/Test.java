
public class Test {
public static void main(String[] args) {
	String tableName = "dbo.SFDC_Account";
	   tableName = tableName.replace("dbo.SFDC_", "").replace("_Temp", "").trim();
	   System.out.println(tableName);
}
}
