#!/usr/bin/env groovy

class Workflow{
  def state = [:]

  def rightShift(fs){
    split(fs)    
    this
  }

  def and(f){
     f()
     this
  }
 
  private def split(fs){
    println "Split: " + fs
    //TODO: change to real concurrent code 
    fs*.call()
  }
  
    
}

def action1 = {
  println "Action 1"
}

def action2 = {
  println "Action 2"
}

S = new Workflow()
S >> [ 
	action1,
        action2
     ] >> {println "Joined"}  >> {println "Step 2"}
