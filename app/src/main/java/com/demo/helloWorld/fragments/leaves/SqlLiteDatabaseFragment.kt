package com.demo.helloWorld.fragments.leaves

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.demo.helloWorld.R
import com.demo.helloWorld.databinding.FragmentSqlLiteDatabaseBinding
import com.demo.helloWorld.handlers.database.DbHelper


class SqlLiteDatabaseFragment : Fragment() {

    lateinit var binding: FragmentSqlLiteDatabaseBinding
    private lateinit var sqlDb: SQLiteDatabase
    private lateinit var sqlDbStatement: SQLiteStatement

    // get total numbers
    private fun setFactNumber(): Int {
        val mCount: Cursor = sqlDb.rawQuery("select count(*) from ${DbHelper.TABLENAME}", null)
        mCount.moveToFirst()
        val id = mCount.getInt(0)
        mCount.close()
        return id
    }

    // get previous fact
    private fun getPreFact(): Triple<Int, String, String>{
        val query = "SELECT ${DbHelper.COL_1}, ${DbHelper.COL_2}, ${DbHelper.COL_3} FROM ${DbHelper.TABLENAME} ORDER BY ${DbHelper.COL_1} DESC LIMIT 1"
        sqlDb.rawQuery( query, null)
            .use {
                if(it.moveToFirst()){
                    return Triple(it.getInt(0), it.getString(1), it.getString(2))
                }
            }
        return Triple(-1, "", "")
    }

    private fun setValue(){
        val preVal = getPreFact()
        binding.results.text = getString(
            R.string.results,
            preVal.first,
            preVal.second,
            preVal.third
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqlDb = DbHelper(activity?:requireActivity()).writableDatabase
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSqlLiteDatabaseBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.factNoLabel.text = getString(R.string.fact_number_d, setFactNumber() + 1)
        binding.results.text = getString(R.string.results, 0, "", "")

        binding.submitFact.setOnClickListener{
            val query = "INSERT INTO ${DbHelper.TABLENAME}  VALUES(?,?,?,?);"
            sqlDbStatement = sqlDb.compileStatement(query)

            sqlDbStatement.bindString(2, binding.factTitleEBox.text.toString())
            sqlDbStatement.bindString(3, binding.factsEBox.text.toString())
            sqlDbStatement.bindNull(4)
            sqlDbStatement.bindNull(1)

            val row = sqlDbStatement.executeInsert()
            sqlDbStatement.clearBindings()

            if(row == 0L) Toast.makeText(requireActivity(), "No Data Inserted: Fail", Toast.LENGTH_SHORT).show()
            else{
                binding.factTitleEBox.setText("")
                binding.factsEBox.setText("")
                Toast.makeText(requireActivity(), "Data successfully added", Toast.LENGTH_SHORT).show()
                binding.factNoLabel.text = getString(R.string.fact_number_d, setFactNumber() + 1)
                setValue()
            }
        }
        setValue()
    }
}