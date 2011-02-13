#!/usr/bin/env groovy

class Workflow{
  def state = [:]

  def rightShift(fs){
    split(fs)    
    this
  }

  def leftShift(f){
     f()
     this
  }
 
  private def split(fs){
    println "Split: " + fs
    //TODO: change to real concurrent code 
    def it = fs.iterator()
    while(it.hasNext()){
      def f = it.next()
      f()
    }
  }
  
    
}

S = new Workflow()
S >> [ 
      {a -> println "Step 1: Flow 1"},
      {b -> println "Step 2: Flow 2"}
     ] << {println "Joined"}  >> [{println "Step 2"}]
