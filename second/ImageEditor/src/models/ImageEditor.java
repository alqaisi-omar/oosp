package models;

   
public interface ImageEditor {
    void open(String filePath);        
    void crop();                      
    void resize(double scale);         
    void applyFilter(String filterName);   
    void save(String outputPath);    
}
