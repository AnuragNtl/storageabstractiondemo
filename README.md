# storageabstractiondemo
Java Library to abstract the underlying storage,retrieval and manipulation of files and dirs.

<h3>Description</h3>
<p>
  In this demonstration, I have created a library with the help of which file storage and retreival in an application can be done without implementing the storage-specific code for each storage provider, such as local filesystem,db or s3.
  Just download the driver JAR for each and make changes in the config file.
  </p>
<h3>Usage:</h3>
<ul>
  <li>Use leucinestorage.jar in your CLASSPATH 
  </li>
  <li>Use a storage provider (which implements) leucinestorage in your CLASSPATH. As examples two providers are available:
  <ul>
    <li>
    filesystemstorage.jar : Store in local filesystem  
    </li>
    <li>
    mysqlstorage.jar : Store in MYSQL databases
    </li>
    <li>
      <a href="https://anuragntl.github.io/storageabstractiondemo/">Read documentation</a> for implementing your own. 
    </li>
    </ul>
  </li>
  <li>Create StorageProvider.conf in your application path.
    <ul>
      <li>
        First line is the Storage Provider class.
      </li>
      <li>
        The rest of the lines are options of the storage provider:
        Example: <a href="demo/StorageProvider - For MySQL.conf">Example Conf</a>
      </li>
    </ul>
  </li>
  
  
  </ul>
  
