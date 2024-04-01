package utilities;

import java.util.ArrayList;

import constants.FilePaths;
import entities.Branch;

/**
 * The {@link LoadStaffs} class loads Branch data from the CSV database
 */
public class LoadBranches extends LoadData<Branch>{
    /**
     * The {@link loadDatafromCSV} method in this class loads in Branch data from branch_list.csv 
     * @return a list of Branch objects with information loaded in
     */
    @Override
    public ArrayList<Branch> loadDatafromCSV(){
        ArrayList<Branch> branches = new ArrayList<>();

        // load data from the branch list csv
        ArrayList<String> serialisedData = SerialiseCSV.readCSV(FilePaths.branchListPath.getPath());

        for(String s:serialisedData){
            String[] row = s.split(",");
            if (s.isEmpty() || s.contains("Name,") || s.contains("Location") || row.length < 2)
                continue;
            
            String name = row[0];
            String location = row[1];
            int quota = Integer.parseInt(row[2].trim());

            Branch tempBranch = new Branch(name, location, quota);
            branches.add(tempBranch);
        }
        return branches;
    }
}
