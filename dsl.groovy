#!/usr/bin/env groovy

class Workflow{
  def state = [:]

  def rightShift(fs){
    if (fs instanceof Workflow) {
      return fs 
    } else {
      split(fs)
    }    
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

def ifTrue = { condition, closure1, closure2 -> if (condition) closure1.call(); else closure2.call()}

S = new Workflow()
F = new Workflow()

S >> (L1 =[ 
	action1,
        action2
      ]) >> ifTrue ? {s -> println s}  >> {println "Way 1"} : 
                   {println "Way 2"}
           
