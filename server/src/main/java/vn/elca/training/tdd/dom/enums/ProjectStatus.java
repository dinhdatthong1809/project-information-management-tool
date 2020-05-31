package vn.elca.training.tdd.dom.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import vn.elca.training.tdd.helpers.TranslateHelper;

import java.util.Arrays;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProjectStatus {
    
    NEW("status.new"),
    
    PLA("status.planned"),
    
    INP("status.inProgress"),
    
    FIN("status.finished");
    
    private String id = this.toString();
    
    private String description;
    
    ProjectStatus(String description) {
        this.description = description;
    }
    
    public static List<ProjectStatus> getList() {
        return Arrays.asList(ProjectStatus.values());
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getDescription() {
        return TranslateHelper.toLocale(description);
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}
