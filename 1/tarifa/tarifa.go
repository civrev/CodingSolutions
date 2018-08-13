// https://open.kattis.com/problems/tarifa

package main

import "fmt"

func main() {

    // declare some strongly typed vars
    var monthly, cases, extra, usage int

    extra = 0

    // input reads in like C
    fmt.Scanln(&monthly)
    fmt.Scanln(&cases)

    for i := 0; i < cases; i++ {

        fmt.Scanln(&usage)

        extra += monthly - usage

    }

    extra += monthly

    fmt.Println(extra)
}

