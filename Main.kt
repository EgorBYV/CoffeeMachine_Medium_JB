package machine

class CoffeeMachine {

    companion object Supplements {
        var water = 400
        var milk = 540
        var beans = 120
        var disCups = 9
        var money = 550

        object BuyCoffee {
            private fun buyCoffee(coffee: Coffee) {
                val message = checkResources(coffee)
                if (message == "I have enough resources, making you a coffee!") {
                    println(message)
                    water -= coffee.water
                    milk -= coffee.milk
                    beans -= coffee.beans
                    disCups -= coffee.disCups
                    money += coffee.cost
                } else println(message)
            }

            private fun checkResources(coffee: Coffee): String {
                return if (water < coffee.water) "Sorry, not enough water!"
                else if (milk < coffee.milk) "Sorry, not enough milk!"
                else if (beans < coffee.beans) "Sorry, not enough coffee beans!"
                else if (disCups < coffee.disCups) "Sorry, not enough coffee beans!"
                else "I have enough resources, making you a coffee!"
            }

            fun chooseCoffee() {
                println(States.COFFEE.message)
                when (readln()) {
                    "1" -> buyCoffee(Coffee.ESPRESSO)
                    "2" -> buyCoffee(Coffee.LATTE)
                    "3" -> buyCoffee(Coffee.CAPPUCCINO)
                }
            }
        }

        fun fill() {
            println("Write how many ml of water do you want to add:")
            water += readln().toInt()
            println("Write how many ml of milk do you want to add:")
            milk += readln().toInt()
            println("Write how many grams of coffee beans do you want to add:")
            beans += readln().toInt()
            println("Write how many disposable cups of coffee do you want to add:")
            disCups += readln().toInt()
        }

        fun take() {
            println("I gave you $${money}")
            money = 0
        }

        fun remaining() = println("The coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$beans of coffee beans\n" +
                "$disCups of disposable cups\n" +
                "$money of money")

    }

    enum class Coffee(val water: Int, val milk: Int, val beans: Int, val cost: Int, val disCups: Int = 1) {
        ESPRESSO(250, 0, 16, 4 ),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6)
    }

    enum class States(val message: String) {
        ACTION("\nWrite action (buy, fill, take, remaining, exit):"),
        COFFEE("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"),
    }

    fun startCoffeeMachine() {
        var stop = false
        while (!stop) {
            println(States.ACTION.message)
            when (readln()) {
                "buy" -> BuyCoffee.chooseCoffee()
                "fill" -> fill()
                "take" -> take()
                "remaining" -> remaining()
                "exit" -> stop = true
            }
        }
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine()
    coffeeMachine.startCoffeeMachine()
}