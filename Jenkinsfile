def remote = [:]
remote.name = 'rita'
remote.host = 'cityscape-rita-server.eurodyn.com'
remote.allowAnyHosts = true
node
{
    stage 'Clone the project'
    git branch: 'master',
    url: 'https://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@github.com/HeliasEurodyn/sofia.git'
    dir('')
    {
        stage("Build")
        {
            sh "chmod +x mvnw"
            sh "./mvnw clean install -DskipTests"
        }
        stage("Run Unit Tests")
        {
            try {
                sh "./mvnw test"
            } catch(err) {
                throw err
            }
        }
        stage("Deploy")
        {
            withCredentials([usernamePassword(credentialsId: 'rita', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')])
            {
                remote.user = USERNAME
                remote.password = PASSWORD
                sshCommand remote: remote, command: "cd /root/rita-docker/backend-container;docker-compose down"
                sshRemove remote: remote, path: '/root/rita-docker/backend-container/rita-backend/sofia-0.0.1.jar'
                sshPut remote: remote, from: './target/sofia-0.0.1.jar', into: '/root/rita-docker/backend-container/rita-backend/'
                sshCommand remote: remote, command: "cd /root/rita-docker/backend-container;docker-compose up --build -d"
            }
        }
    }
}
