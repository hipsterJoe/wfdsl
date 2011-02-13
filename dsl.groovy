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
    state = fs*.call(state)
  }
  
    
}

def action1 = {
  println "Action 1"
  true
}

def action2 = {
  println "Action 2"
  [1, 2, 3]
}

S = new Workflow()
S >> [ 
	action1,
        action2
     ] >> {s -> println s}  >> {println "Step 2"}
