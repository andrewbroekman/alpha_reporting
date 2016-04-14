
package com.codinginfinity.research.people;

/**
 * ReqEntity - person or group
 * @author 
 */
public class ReqEntity {

    /**
     * 
     * @param entity
     * @param name 
     */
    public ReqEntity(String entity, String name) {
        this.entity = entity;
        this.name = name;
    }

    /**
     * 
     * @return 
     */
    public String getType() {
        return entity;
    }

    /**
     * 
     * @param entity 
     */
    public void setType(String entity) {
        this.entity = entity;
    }   

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    private String entity;
    private String name;
    
    
}
