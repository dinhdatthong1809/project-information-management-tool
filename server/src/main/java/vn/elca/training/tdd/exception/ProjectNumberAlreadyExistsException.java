package vn.elca.training.tdd.exception;

public class ProjectNumberAlreadyExistsException extends RuntimeException {
    
    public ProjectNumberAlreadyExistsException(Integer projectNumber) {
        super("Project with number " + projectNumber + " is already existed");
    }
    
}
