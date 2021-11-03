pipeline {	

	agent any 
	
	environment { 
        registry = "alouiahmed/timesheet" 
		
       
    }

	stages{
			
			stage('Clean Package'){
					steps{
						bat "mvn clean package"
					}				
				}
				
			stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
            }

            stage('Nexus Deploy'){
				steps{
					bat "mvn deploy"
				}				
			}
		
			
			

			
		}
	}