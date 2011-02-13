#!/usr/bin/env groovy

class Workflow{
  def state = [:]

  def rightShift(fs){
    split(fs)    
    this
  }

  def or(f){
     f()
     this
  }
 
  private def split(fs){
    println "Split: " + fs
    //TODO: change to real concurrent code 
    fs*.call()
  }
  
    
}

S = new Workflow()
S >> [ 
      {a -> println "Step 1: Flow 1"},
      {b -> println "Step 1: Flow 2"}
     ] | {println "Joined"}  >> [{println "Step 2"}]
