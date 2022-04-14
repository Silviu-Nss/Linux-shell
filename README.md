Shell implementation which allows executing modification / query commands on a file system.
Design patterns used: Composite, Command, Singleton and Factory.

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
 
 
Note: the paths for ls (without -R), rm , touch and mkdir can have "*", "." or "..". 
