/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.ref;


public enum ActionType {
    
	UPLOAD_FILE("Upload file"),
    UPADATE("Update"),
    APPROVAL("Approval"),
    VIEW("View"),
    CREATE("Create"),
    DELETE("Delete"),    
	SAVE("Save"),  
	ACCESS_PAGE("Access page"),
	RESULT("Result");
	
    private String name;

    ActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

