package com.demo.helloWorld.records

import com.demo.helloWorld.*
import com.demo.helloWorld.data.*

import kotlin.random.Random

object StaticRecords {

    private val alphabetList: List<String> = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")

    private val imageList: List<Int> = listOf(
        R.drawable.icecream_small1,
        R.drawable.icecream_small2,
        R.drawable.icecream_small3,
        R.drawable.icecream_small4,
        R.drawable.icecream_small5,
        R.drawable.icecream_small6,
        R.drawable.icecream_small7,
        R.drawable.icecream_small8,
        R.drawable.icecream_small9,
        R.drawable.icecream_small10,
    ).shuffled()

    private val factList: List<String> = listOf(
        "It takes 12 pounds of milk to produce just 1 gallon of ice cream",
        "The country that consumes the most ice cream is USA, followed by Australia then Norway",
        "Chocolate ice cream was invented before vanilla",
        "Ice cream in America in the 1700’s was rare and enjoyed by the elite",
        "Industrial ice cream production in the US began in 1851",
        "The average number of licks to finish a scoop of ice cream is 50",
        "The most popular flavor is vanilla, then chocolate",
        "Vanilla was rare and exotic in the late 1700’s",
        "The first written ice cream recipe was found in a 1665 recipe book",
        "The majority of Americans – around 90% have ice cream in their freezers"
    ).shuffled()

    private val cardList: MutableList<Card> = mutableListOf()

    fun prepareCardList(){
        var id = 0
        while(id < imageList.size){
            cardList.add(
                Card(
                    imageList[id],
                    "Fact ${id + 1}",
                    factList[id]
                )
            )
            id++
        }
    }

    fun multiplyAlphaList(n: Int): Array<String>{
        val multipleArray : MutableList<String> = mutableListOf()
        for(lst in 1..n){
            multipleArray += alphabetList
        }

        return multipleArray.toTypedArray()
    }

    private fun multiplyCards(n: Int): MutableList<Card> {
        val multipleArray : MutableList<Card> = mutableListOf()
        for(lst in 1..n){
            multipleArray += cardList
        }

        return multipleArray
    }

    fun multiplyCardList(n: Int): Array<Card>{
        return multiplyCards(n).toTypedArray()
    }

    fun generateAlphaWithCard(minAlpha: Int = 1, maxAlpha: Int = 15, minCardMul : Int = 1,  maxCardMul : Int = 5): List<Pair<String, List<Card>>> {

        return try{
            alphabetList
                .subList(0, Random.nextInt(minAlpha, maxAlpha))
                .map {
                    Pair(it, multiplyCards(Random.nextInt(minCardMul, maxCardMul)))
                }
        } catch (e: Exception){
            alphabetList
                .subList(0, 5)
                .map {
                    Pair(it, multiplyCards(1))
                }
        }

    }

    fun generateMultiviewData(n: Int): List<MultiviewData> {
        val newList = mutableListOf<MultiviewData>()
        multiplyCards(n).forEach {
            newList.add(MultiviewData(it, LayoutType.values().random()))
        }
        return newList
    }
}