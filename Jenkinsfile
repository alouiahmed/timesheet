pipeline {	

	agent any 
	
	stages{ 
			
			stage('Test & Build'){
				steps{
					bat "mvn clean package"

				}				
			}
			
			stage('Nexus Deploy'){
				steps{
					bat "mvn deploy"
				}				
			}
				
			stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
            }

			
			stage('Cleanup Workspace'){
				steps{
					cleanWs()
				}				
			}
			
		
			
		
			
			

			
		}
	}