package vzap.losh.user;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import vzap.losh.user.*;

public class UserModelClass extends AbstractTableModel {

	public final static int USERID = 0;
	public final static int FIRSTNAME =1 ;
	public final static int SURNAME =2;
	public final static int ISADMIN = 3;
	public final static boolean ISADMIN_YES = true;
	public final static boolean ISADMIN_NO = false;
	
	public boolean DEBUG=false;
	
	public Object[][] values;//=loadlist(); //= {{"losh", "test", "Loshen", "Naicker", new Boolean(ISADMIN_YES)}};
	public final static String[] COLUMN_NAMES = {"User Name", "First Name", "Last Name", "Is Admin"};
	private static final long serialVersionUID = 1L;
	
	public  Object[][] loadlist(ArrayList<User> userList) {
		// TODO Auto-generated method stub
		values = new Object [userList.size()][4];
		
		for (int x=0;   x  <userList.size(); x++)
		{
			values[x][0] = userList.get(x).getUserID();
			values[x][1] = userList.get(x).getFirstName();
			values[x][2] = userList.get(x).getSurName();
			values[x][3] = userList.get(x).isAdmin()?new Boolean(ISADMIN_YES):new Boolean(ISADMIN_NO);
		}
		return values;
	}
	private Object[] addRowModel(User user)
	{
		Object[] value= new Object[4];
		value[0] = user.getUserID();
		value[1] = user.getFirstName();
		value[2] = user.getSurName();
		value[3] = user.isAdmin()?new Boolean(ISADMIN_YES):new Boolean(ISADMIN_NO);	
		//value);
		
	    //public synchronized void addElement(E obj) {
		//fireTableDataChanged(); //needed
		return value;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	@Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col].toString();
    }
	@Override
    public int getRowCount() { return values.length; }

	@Override
	public int getColumnCount() { return COLUMN_NAMES.length; }
    
	
	public Object getValueAt(int row, int col) {
        return values[row][col];
    }
  
  

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
         return getValueAt(0, c).getClass();
     }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
//    public boolean isCellEditable(int row, int col) {
//        //Note that the data/cell address is constant,
//        //no matter where the cell appears onscreen.
//        if (col < 2) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
     public void setValueAt(Object value, int row, int col) {
         if (DEBUG) {
             System.out.println("Setting value at " + row + "," + col
                               + " to " + value
                               + " (an instance of "
                               + value.getClass() + ")");
        }

        values[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + values[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}

