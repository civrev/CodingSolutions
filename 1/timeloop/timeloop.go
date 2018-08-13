// https://open.kattis.com/problems/timeloop

package main

import "fmt"


func main(){
    //if you're planning on scanning something in
    //better strongly type it
    //to do that use var NAME TYPE
    //types in Go are NOT compatible, which may cause a problem if you often infer types
    //using int and not int8,16,32, or 64
    var times int

    //you can also infer types with colon (:) operator
    message := "Abracadabra"

    //to scan in use the pointer!
    fmt.Scanln(&times)

    for i := 1; i <= times; i++{
        //like in Python a comma here will place a space in between
        fmt.Println(i, message)
    }
}
