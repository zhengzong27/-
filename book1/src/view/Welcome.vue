<template>
  <el-card>
    <el-row :gutter="10">
      <el-card>
        <el-col :span="12" style="background-color: cadetblue">
          <div id="bar" style="width: auto;height: 400px"></div>
        </el-col>
        <el-col :span="12" style="background-color: chartreuse">
          <div id="pie" style="width: auto;height: 400px"></div>
        </el-col>
      </el-card>
      <el-card style="background-color: blanchedalmond">
        <el-row>
          <el-col>
            <div id="line" style="width: auto;height: 400px"></div>
          </el-col>
        </el-row>
      </el-card>

    </el-row>
  </el-card>
</template>
<script>
export default {
  data() {
    return {}
  },
  methods: {
    showLine() {
      let line = this.$echarts.init(document.getElementById("line"));
      line.setOption({
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: 'line'
          }
        ]
      })
    },
    showPie() {
      let pie = this.$echarts.init(document.getElementById("pie"));
      pie.setOption({
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: 1048, name: '文学类'},
              {value: 735, name: '考研类'},
              {value: 580, name: '卡漫类'},
              {value: 484, name: '政治类'},
              {value: 300, name: '少儿类'}
            ]
          }
        ]
      })

    },
    showBar() {
      let bar = this.$echarts.init(document.getElementById("bar"));
      this.$http.get('http://localhost/borrow/getNamesBorrowCount').then(res => {
        let names = [];
        let quantites = [];
        let array = res.data.data;
        for (let i in array) {
          names.push(array[i].name)
          quantites.push(array[i].quantity)
        }
        bar.setOption({
          title: {
            text: '借阅人借阅数量统计'
          },
          tooltip: {},
          legend: {
            data: ['借阅人借阅数量统计']
          },
          xAxis: {
            data: names
          },
          yAxis: {},
          series: [
            {
              name: '借阅人借阅数量统计',
              type: 'bar',
              data: quantites
            }
          ]
        })
      })

    }

  },
  mounted() {
    this.showBar()
    this.showPie()
    this.showLine()
  }
}

</script>
<style scoped>


</style>
