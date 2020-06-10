/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jenkins

def name='admin'

println "Hello $name!"

node{
    echo 'variables configuracion'
    def mvnHome = tool 'M3'
    
    // -- Descarga codigo
    echo 'descarga codigo'
    sh 'rm -rf *'
    checkout scm
}
