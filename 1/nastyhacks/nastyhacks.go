// https://open.kattis.com/problems/nastyhacks

package main

import "fmt"

func main(){

    var cases, r, e, c int

    fmt.Scanln(&cases)

    for i := 0; i < cases; i++ {
        r, e, c = readLine()
        evaluate(r, e, c)
    }
}

// Go can return mutiple values from a function
// those values can be enclosed in (), and uniquely, they can be named
func readLine() (r, e, c int){
    fmt.Scanln(&r, &e, &c)
    return
}

func evaluate(r, e, c int){
    if r == (e - c){
        fmt.Println("does not matter")
    }else if r < (e - c) {
        fmt.Println("advertise")
    }else{
        fmt.Println("do not advertise")
    }
}
