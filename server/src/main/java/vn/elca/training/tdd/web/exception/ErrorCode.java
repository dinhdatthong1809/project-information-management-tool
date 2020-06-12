package vn.elca.training.tdd.web.exception;

public enum ErrorCode {
    
    PROJECT_NUMBER_EXISTED(1),
    
    PROJECT_INVALID_DELETE(2),
    
    PROJECT_CONCURRENT_UPDATE(3),
    
    PROJECT_CONCURRENT_DELETE(4),
    
    INVALID_END_DATE(5);
    
    private int value;
    
    ErrorCode(int value) {
        this.value = value;
    }
    
    public int value() {
        return this.value;
    }
}
