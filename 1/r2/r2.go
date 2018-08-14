// https://open.kattis.com/problems/r2

package main

import "fmt"

// user defined types can be used with struct to utilize class-like functionality
// in Go, the visibility is set using convention
// meaning that Capital letter variables are public outside this package
// and lowercase is not

//capital means public
type MeanSet struct{
    one, two, mean int
}

// you can short hand args the same way you can when you decalre variables
// also, similar to how vars are typed to the right so are return values from functions
func getFromMean(one, mean int) int{
    return (mean * 2) - one
}

func main(){
    var ms = MeanSet{}
    
    fmt.Scanln(&ms.one, &ms.mean)

    ms.two = getFromMean(ms.one, ms.mean)

    fmt.Println(ms.two)
}
