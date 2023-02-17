package com.demo.helloWorld.handlers.navigationAction

import com.demo.helloWorld.ActivityLifeCycleDemoActivity
import com.demo.helloWorld.R
import com.demo.helloWorld.fragments.leaves.*
import com.demo.helloWorld.fragments.navigation.MainActivityContent

object NavigationActionRecord {
    private val navList: List<ExpandedMenuItemModel> =
        listOf(
            ExpandedMenuItemModel(R.id.OpenIntentDemo, ExplicitIntentFragment::class, 0),
            ExpandedMenuItemModel(R.id.OpenImplicitIntentsDemo, ImplicitIntentFragment::class),
            ExpandedMenuItemModel(R.id.OpenIntentResultDemo, ExplicitIntentFragment::class, 1),
            ExpandedMenuItemModel(R.id.OpenNotificationDemo, LocalNotificationFragment::class),
            ExpandedMenuItemModel(R.id.OpenAlertBox, AlertDialogFragment::class),
            ExpandedMenuItemModel(R.id.OpenCustomAlertBoxDemo, CustomAlertDialogFragment::class),
            ExpandedMenuItemModel(R.id.OpenActivityLifeCycleDemo, ActivityLifeCycleDemoActivity::class, -1 ,false),
            ExpandedMenuItemModel(R.id.OpenFragmentLifeCycleDemo, ActivityLifeCycleFragment::class),
            ExpandedMenuItemModel(R.id.OpenBottomSheetDemo, BottomSheetFragment::class),
            ExpandedMenuItemModel(R.id.OpenPersistentBottomSheetDemo, PersistentBottomSheetFragment::class),
            ExpandedMenuItemModel(R.id.OpenSharedPreferencesDemo, SharedPreferencesFragment::class),
            ExpandedMenuItemModel(R.id.OpenSqlLiteDatabaseInteractionDemo, SqlLiteDatabaseFragment::class),
            ExpandedMenuItemModel(R.id.OpenDisplayImageDemo, InternalStorageImageFragment::class),
            ExpandedMenuItemModel(R.id.OpenThreadHandlerDemo, ThreadHandlerFragment::class),
            ExpandedMenuItemModel(R.id.OpenAsyncTaskDemo, AsyncTaskFragment::class),
            ExpandedMenuItemModel(R.id.OpenBroadcastReceiverDemo, BroadcastReceiverFragment::class),
            ExpandedMenuItemModel(R.id.OpenLayoutDemo, ListViewFragment::class),
            ExpandedMenuItemModel(R.id.OpenExpandableListViewDemo, ExpandableListFragment::class),
            ExpandedMenuItemModel(R.id.OpenBaseAdapterHomeDemo, BaseAdapterDemoFragment::class, 1),
            ExpandedMenuItemModel(R.id.OpenBaseAdapterHomeViewHolderDemo, BaseAdapterDemoFragment::class, 0),
            ExpandedMenuItemModel(R.id.OpenRecyclerViewDemoV1, RecyclerViewFragment::class, 1),
            ExpandedMenuItemModel(R.id.openRecyclerViewDemoV2, RecyclerViewFragment::class, 2),
            ExpandedMenuItemModel(R.id.openRecyclerViewDemoV3, StagedViewFragment::class, 0),
            ExpandedMenuItemModel(R.id.OpenGridLayoutDemo, StagedViewFragment::class, 2),
            ExpandedMenuItemModel(R.id.OpenStagedLayoutDemo, StagedViewFragment::class, 1),
            ExpandedMenuItemModel(R.id.OpenMultiViewRecyclerViewDemo, StagedViewFragment::class, 3),
            ExpandedMenuItemModel(R.id.OpenNestedRecyclerViewDemo, NestedRecyclerViewFragment::class)
        )
    fun getObject(id: Int) = navList.find { it.menuItemId == id } ?: ExpandedMenuItemModel(0, MainActivityContent::class)
}