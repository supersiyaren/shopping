"use strict";

function _toConsumableArray(arr) { return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _nonIterableSpread(); }

function _nonIterableSpread() { throw new TypeError("Invalid attempt to spread non-iterable instance"); }

function _iterableToArray(iter) { if (Symbol.iterator in Object(iter) || Object.prototype.toString.call(iter) === "[object Arguments]") return Array.from(iter); }

function _arrayWithoutHoles(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = new Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

var lineChart =
/*#__PURE__*/
function () {
  function lineChart(id, _ref) {
    var _ref$title = _ref.title,
        title = _ref$title === void 0 ? "" : _ref$title,
        _ref$series = _ref.series,
        series = _ref$series === void 0 ? [] : _ref$series,
        _ref$xAxis = _ref.xAxis,
        xAxis = _ref$xAxis === void 0 ? {
      data: []
    } : _ref$xAxis;

    _classCallCheck(this, lineChart);

    this.colors = ["#FD7A4F", "#FDD764", "#7359C3", "#42C288", "#92E98E", "#2E8AE6", "#44C0EA", "#3C52C9", "#4Dd62196"];
    this.c = document.getElementById(id);
    this.width = this.c.parentNode.clientWidth * 4;
    this.height = this.c.parentNode.clientHeight * 4;
    this.c.width = this.width;
    this.c.height = this.height;
    this.c.style.width = this.width / 4 + 'px';
    this.c.style.height = this.height / 4 + 'px';
    this.context = this.c.getContext("2d");
    this.context.width = this.size;
    this.title = title;
    this.series = series;
    this.xAxis = xAxis;
    this.xAxisPoint = [];
    this.left = this.width * 0.12;
    this.right = this.width * 0.95;
    this.top = this.height * 0.1;
    this.lineTop = this.height * 0.2;
    this.bottom = this.height * 0.8;
    this.lineBottom = this.height * 0.7;
    this.everyWidth = 0; //x轴每个标记所占的宽度

    this.everyHeight = 0; //每个数值所占的高度

    this.setXaxisPoint(xAxis.data);
    this.drawLine();
  }

  _createClass(lineChart, [{
    key: "setXaxisPoint",
    value: function setXaxisPoint(xAxis) {
      var everyWidth = this.width * (0.95 - 0.1) / xAxis.length;
      this.everyWidth = everyWidth;

      for (var i = 0; i < xAxis.length; i++) {
        this.xAxisPoint.push(this.left + (i + 0.5) * everyWidth);
      }
    }
  }, {
    key: "drawLine",
    value: function drawLine() {
      var _this = this;

      // 标题
      this.context.font = (this.width * 0.04 > 80 ? 80 : this.width * 0.04) + "px Helvetica";
      this.context.textAlign = "center";
      this.context.fillText(this.title, this.width / 2, this.height * 0.08, this.width);
      this.context.fillStyle = '#333';
      this.context.strokeStyle = '#666';
      this.context.lineWidth = 1;
      this.context.beginPath();
      this.context.moveTo(this.left, this.top);
      this.context.lineTo(this.left, this.bottom);
      this.context.lineTo(this.right, this.bottom);
      this.context.stroke();
      this.context.font = (this.width * 0.03 > 60 ? 60 : this.width * 0.03) + "px Helvetica";
      this.context.textAlign = 'center'; // 横坐标标记

      this.xAxis.data.map(function (item, i) {
        var x = _this.xAxisPoint[i];

        _this.context.fillText(item, x, _this.bottom + _this.height * 0.05, _this.everyWidth);
      }); // 计算纵坐标

      var allData = [];
      this.series.map(function (item, i) {
        allData = [].concat(_toConsumableArray(allData), _toConsumableArray(item.data));
      });
      var min = Math.min.apply(Math, _toConsumableArray(allData));
      var max = Math.max.apply(Math, _toConsumableArray(allData));
      this.everyHeight = (this.lineBottom - this.lineTop) / (max - min);
      var stepNum = (max - min) / 5;
      var stepHeight = (this.lineBottom - this.lineTop) / 4;
      this.context.textAlign = 'right'; // 纵坐标标记

      for (var i = 0; i < 5; i++) {
        var _this$context;

        var positon = [this.left - this.width * 0.02, this.lineBottom - stepHeight * i];
        this.context.beginPath();

        (_this$context = this.context).fillText.apply(_this$context, [min + stepNum * i].concat(positon, [this.left]));
      } // 折线图


      this.series.map(function (item, i) {
        _this.context.strokeStyle = _this.colors[i];
        _this.context.fillStyle = "#fff";
        _this.context.lineWidth = _this.width * 0.002;
        _this.context.lineJoin = "round";

        _this.context.beginPath();

        var datas = item.data;
        var yAxis = [];

        for (var n = 0; n < datas.length; n++) {
          var y = _this.lineBottom - (datas[n] - min) * _this.everyHeight;
          yAxis.push(y);

          if (n == 0) {
            _this.context.moveTo(_this.xAxisPoint[n], y);
          } else {
            _this.context.lineTo(_this.xAxisPoint[n], y);
          }
        }

        _this.context.stroke();

        if (item.type == "area") {
          _this.context.beginPath();

          _this.context.fillStyle = _this.colors[i];
          _this.context.globalAlpha = 0.1;

          _this.context.moveTo(_this.xAxisPoint[_this.xAxisPoint.length - 1], _this.bottom);

          _this.context.lineTo(_this.xAxisPoint[0], _this.bottom);

          for (var _n = 0; _n < datas.length; _n++) {
            _this.context.lineTo(_this.xAxisPoint[_n], yAxis[_n]);
          }

          _this.context.fill();
        }

        _this.context.globalAlpha = 1;
        _this.context.fillStyle = "#fff";

        for (var _n2 = 0; _n2 < datas.length; _n2++) {
          _this.context.beginPath();

          _this.context.lineWidth = _this.width * 0.004;

          _this.context.arc(_this.xAxisPoint[_n2], yAxis[_n2], _this.width * 0.004, 0, Math.PI * 2);

          _this.context.stroke();

          _this.context.fill();
        }
      }); //计算标注起始位置

      this.context.font = (this.width * 0.02 > 50 ? 50 : this.width * 0.02) + "px Helvetica";
      var textWidth = 0;
      var textStartXaxis = this.width * 0.05;
      this.series.map(function (item, i) {
        var text = _this.xAxis.data[i];
        textWidth += _this.context.measureText(text).width + _this.width * 0.08;
      });

      if (textWidth < this.width * 0.9) {
        textStartXaxis = (this.width - textWidth) / 2;
      } else {
        textStartXaxis = this.width * 0.05;
      } //画出标注


      this.series.map(function (item, i) {
        var text = item.name;
        _this.context.strokeStyle = _this.colors[i];
        _this.context.fillStyle = "#fff"; // 线

        _this.context.beginPath();

        _this.context.moveTo(textStartXaxis - _this.width * 0.015, _this.height * 0.95);

        _this.context.lineTo(textStartXaxis + _this.width * 0.015, _this.height * 0.95);

        _this.context.stroke(); // 圆圈


        _this.context.beginPath();

        _this.context.lineWidth = _this.width * 0.004;

        _this.context.arc(textStartXaxis, _this.height * 0.95, _this.width * 0.004, 0, Math.PI * 2);

        _this.context.stroke();

        _this.context.fill(); //name


        _this.context.beginPath();

        _this.context.fillStyle = "#999";
        _this.context.textAlign = "left";
        _this.context.textBaseline = 'middle';

        _this.context.fillText(text, textStartXaxis + _this.width * 0.03, _this.height * 0.95, _this.width * 0.6);

        _this.context.fill();

        textStartXaxis += _this.context.measureText(text).width + _this.width * 0.08;
      });
    }
  }]);

  return lineChart;
}();

var barChart =
/*#__PURE__*/
function () {
  function barChart(id, _ref2) {
    var _ref2$title = _ref2.title,
        title = _ref2$title === void 0 ? "" : _ref2$title,
        _ref2$series = _ref2.series,
        series = _ref2$series === void 0 ? [] : _ref2$series,
        _ref2$xAxis = _ref2.xAxis,
        xAxis = _ref2$xAxis === void 0 ? {
      data: []
    } : _ref2$xAxis;

    _classCallCheck(this, barChart);

    this.colors = ["#FD7A4F", "#FDD764", "#7359C3", "#42C288", "#92E98E", "#2E8AE6", "#44C0EA", "#3C52C9", "#4Dd62196"];
    this.c = document.getElementById(id);
    this.width = this.c.parentNode.clientWidth * 4;
    this.height = this.c.parentNode.clientHeight * 4;
    this.c.width = this.width;
    this.c.height = this.height;
    this.c.style.width = this.width / 4 + 'px';
    this.c.style.height = this.height / 4 + 'px';
    this.context = this.c.getContext("2d");
    this.context.width = this.size;
    this.title = title;
    this.series = series;
    this.xAxis = xAxis;
    this.xAxisPoint = [];
    this.left = this.width * 0.12;
    this.right = this.width * 0.95;
    this.top = this.height * 0.1;
    this.lineTop = this.height * 0.2;
    this.bottom = this.height * 0.8;
    this.lineBottom = this.height * 0.7;
    this.everyWidth = 0; //x轴每个标记所占的宽度

    this.everyHeight = 0; //每个数值所占的高度

    this.setXaxisPoint(xAxis.data);
    this.drawLine();
  }

  _createClass(barChart, [{
    key: "setXaxisPoint",
    value: function setXaxisPoint(xAxis) {
      var everyWidth = this.width * (0.95 - 0.1) / xAxis.length;
      this.everyWidth = everyWidth;

      for (var i = 0; i < xAxis.length; i++) {
        this.xAxisPoint.push(this.left + (i + 0.5) * everyWidth);
      }
    }
  }, {
    key: "drawLine",
    value: function drawLine() {
      var _this2 = this;

      // 标题
      this.context.font = (this.width * 0.04 > 80 ? 80 : this.width * 0.04) + "px Helvetica";
      this.context.textAlign = "center";
      this.context.fillText(this.title, this.width / 2, this.height * 0.08, this.width);
      this.context.fillStyle = '#333';
      this.context.strokeStyle = '#666';
      this.context.lineWidth = 1;
      this.context.beginPath();
      this.context.moveTo(this.left, this.top);
      this.context.lineTo(this.left, this.bottom);
      this.context.lineTo(this.right, this.bottom);
      this.context.stroke();
      this.context.font = (this.width * 0.03 > 60 ? 60 : this.width * 0.03) + "px Helvetica";
      this.context.textAlign = 'center'; // 横坐标标记

      this.xAxis.data.map(function (item, i) {
        var x = _this2.xAxisPoint[i];

        _this2.context.fillText(item, x, _this2.bottom + _this2.height * 0.05, _this2.everyWidth);
      }); // 计算纵坐标

      var allData = [];
      this.series.map(function (item, i) {
        allData = [].concat(_toConsumableArray(allData), _toConsumableArray(item.data));
      });
      var min = Math.min.apply(Math, _toConsumableArray(allData));
      var max = Math.max.apply(Math, _toConsumableArray(allData));
      this.everyHeight = (this.lineBottom - this.lineTop) / (max - min);
      var stepNum = (max - min) / 5;
      var stepHeight = (this.lineBottom - this.lineTop) / 4;
      this.context.textAlign = 'right'; // 纵坐标标记

      for (var i = 0; i < 5; i++) {
        var _this$context2;

        var positon = [this.left - this.width * 0.02, this.lineBottom - stepHeight * i];
        this.context.beginPath();

        (_this$context2 = this.context).fillText.apply(_this$context2, [min + stepNum * i].concat(positon, [this.left]));
      } // 柱状图


      var barWidth = this.everyWidth * 0.8 / this.series.length;
      this.series.map(function (item, i) {
        var datas = item.data;
        _this2.context.fillStyle = _this2.colors[i];

        for (var n = 0; n < datas.length; n++) {
          var x = _this2.left + _this2.everyWidth * 0.1 + _this2.everyWidth * n + barWidth * i;
          var h = (datas[n] - min) * _this2.everyHeight + (_this2.bottom - _this2.lineBottom);
          var y = _this2.bottom - h;

          _this2.context.beginPath();

          _this2.context.rect(x, y, barWidth, h);

          _this2.context.fill();
        }
      }); //计算标注起始位置

      this.context.font = (this.width * 0.02 > 50 ? 50 : this.width * 0.02) + "px Helvetica";
      var textWidth = 0;
      var textStartXaxis = this.width * 0.05;
      this.series.map(function (item, i) {
        var text = _this2.xAxis.data[i];
        textWidth += _this2.context.measureText(text).width + _this2.width * 0.08;
      });

      if (textWidth < this.width * 0.9) {
        textStartXaxis = (this.width - textWidth) / 2;
      } else {
        textStartXaxis = this.width * 0.05;
      } //画出标注


      this.series.map(function (item, i) {
        var text = item.name;
        _this2.context.strokeStyle = _this2.colors[i];
        _this2.context.fillStyle = _this2.colors[i];

        _this2.context.beginPath(); //矩形


        _this2.context.rect(textStartXaxis, _this2.height * 0.95 - _this2.height * 0.01, _this2.width * 0.02, _this2.height * 0.02);

        _this2.context.fill(); //name


        _this2.context.beginPath();

        _this2.context.fillStyle = "#999";
        _this2.context.textAlign = "left";
        _this2.context.textBaseline = 'middle';

        _this2.context.fillText(text, textStartXaxis + _this2.width * 0.03, _this2.height * 0.95, _this2.width * 0.6);

        _this2.context.fill();

        textStartXaxis += _this2.context.measureText(text).width + _this2.width * 0.08;
      });
    }
  }]);

  return barChart;
}();

var circleChart =
/*#__PURE__*/
function () {
  function circleChart(id, chartDatas, defalutIndex, callback) {
    _classCallCheck(this, circleChart);

    this.canvas = document.getElementById(id);
    this.size = this.canvas.parentNode.clientWidth * 4;
    this.canvas.style.width = this.size / 4 + "px";
    this.canvas.style.height = this.size / 4 + "px";
    this.canvas.width = this.size;
    this.canvas.height = this.size;
    this.ctx = this.canvas.getContext("2d");
    this.defalutIndex = defalutIndex;
    this.chartDatas = chartDatas;
    this.lineWidth = this.size / 5;
    this.startAngle = -0.5;
    this.callback = callback;
    this.canvas.addEventListener('click', this.mouseDownEvent.bind(this));
    this.AngleList = [];
  }

  _createClass(circleChart, [{
    key: "draw",
    value: function draw() {
      var _this3 = this;

      this.ctx.clearRect(0, 0, this.size, this.size);
      if (this.chartDatas.length == 0) return;
      this.ctx.lineWidth = this.lineWidth;
      this.ctx.lineCap = "butt";
      var startAngle = Math.PI * -0.5;
      var endAngle = startAngle;
      this.AngleList = [];
      this.chartDatas.map(function (item, i) {
        _this3.ctx.beginPath();

        _this3.ctx.strokeStyle = item.color;

        if (i > 0) {
          startAngle = endAngle;
        }

        endAngle = startAngle + item.percent * Math.PI * 2;

        _this3.AngleList.push(endAngle); //选中当前项，需要向外偏移


        if (i == _this3.defalutIndex) {
          //选中当前项，需要向外偏移
          var centerAngle = (startAngle + endAngle) / 2;
          var x = _this3.lineWidth * 0.2 * Math.cos(centerAngle);
          var y = _this3.lineWidth * 0.2 * Math.sin(centerAngle);

          _this3.ctx.arc(_this3.size / 2 + x, _this3.size / 2 + y, _this3.size / 2 - _this3.lineWidth / 2 - _this3.lineWidth * 0.2, startAngle, endAngle);
        } else {
          _this3.ctx.arc(_this3.size / 2, _this3.size / 2, _this3.size / 2 - _this3.lineWidth / 2 - _this3.lineWidth * 0.2, startAngle, endAngle);
        }

        _this3.ctx.stroke();
      });
    }
  }, {
    key: "mouseDownEvent",
    value: function mouseDownEvent(e) {
      var _ref3 = [e.offsetX, e.offsetY],
          x1 = _ref3[0],
          y1 = _ref3[1];
      var x0 = this.size / 2 / 4,
          y0 = this.size / 2 / 4;
      var angle = 0;

      if (x1 > x0) {
        y1 < y0 ? angle = -0.5 * Math.PI + Math.atan((x1 - x0) / (y0 - y1)) : angle = Math.atan((y1 - y0) / (x1 - x0));
      } else {
        y1 < y0 ? angle = Math.PI + Math.atan((y0 - y1) / (x0 - x1)) : angle = Math.atan((x0 - x1) / (y1 - y0)) + Math.PI * 0.5;
      }

      for (var i = 0; i < this.AngleList.length; i++) {
        if (angle < this.AngleList[i]) {
          this.defalutIndex = i;
          this.draw();
          this.callback ? this.callback(i) : '';
          break;
        }
      }
    }
  }]);

  return circleChart;
}();
