This program implements a shell to allow executing some modification / query commands on a file system. It uses the Composite, Command, Singleton and Factory desing patterns.

Commands:

  - ls [< path> ]
  
  - pwd
  
  - cd < path>
  
  - cp < source > < dest_folder >
  
  - mv < source > < dest_folder >
  
  - rm < path >
  
  - touch < file_path >
  
  - mkdir < folder_path >
  
  - grep “< regex >”
 
 
Note: the paths for ls (without -R), rm , touch și mkdir can have "*", "." or "..". 
