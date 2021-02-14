package com.example.pluang.ui.detailedview

import android.os.Build
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pluang.R
import com.example.pluang.Util.animation.SlideAnimationUtil
import com.example.pluang.data.response.Item
import com.example.pluang.ui.base.BaseActivity
import com.example.pluang.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailedActivity : BaseActivity<DetailedViewModel>(
    DetailedViewModel::class
) {

    override fun provideLayoutId(): Int = R.layout.activity_detail

    override fun setupView(savedInstanceState: Bundle?) {


        setAnimation()

        val bundle: Bundle = intent.extras ?: return
        val data = bundle[MainActivity.DATA] as Item

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailed_circleImageView.transitionName =
                bundle[MainActivity.EXTRA_TRANSACTION_IMAGE].toString()
            detailed_repo_name.transitionName =
                bundle[MainActivity.EXTRA_TRANSACTION_REPO].toString()
            detailed_repo_dec.transitionName =
                bundle[MainActivity.EXTRA_TRANSACTION_DESCRIPTION].toString()
        }

        detailed_repo_name.text = data.repo
        Glide.with(this).load(data.avatars[0]).into(detailed_circleImageView)
        detailed_repo_dec.text = data.desc
        tv_forks.text = data.forks
        tv_lang.text = data.lang
        tv_added_star.text = data.added_stars
        tv_stars.text = data.stars
        tv_link.text = data.repo_link

    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    private fun setAnimation() {
        SlideAnimationUtil.slideInFromLeft(this, tv_repo)
        SlideAnimationUtil.slideInFromLeft(this, tv_desc)
        SlideAnimationUtil.slideFromTop(this, tv_forks)
        SlideAnimationUtil.slideFromTop(this, tv_lang)
        SlideAnimationUtil.slideFromTop(this, tv_stars)
        SlideAnimationUtil.slideFromTop(this, iv_star)
        SlideAnimationUtil.slideFromTop(this, iv_fork)
        SlideAnimationUtil.slideFromTop(this, iv_lang)
        SlideAnimationUtil.slideFromDown(this,tv_added_star)
        SlideAnimationUtil.slideFromDown(this,tv_link)

    }
}