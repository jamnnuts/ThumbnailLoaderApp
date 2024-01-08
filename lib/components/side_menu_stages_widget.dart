import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'package:flutter/material.dart';
import 'side_menu_stages_model.dart';
export 'side_menu_stages_model.dart';

class SideMenuStagesWidget extends StatefulWidget {
  const SideMenuStagesWidget({
    super.key,
    required this.stage,
  });

  final int? stage;

  @override
  _SideMenuStagesWidgetState createState() => _SideMenuStagesWidgetState();
}

class _SideMenuStagesWidgetState extends State<SideMenuStagesWidget> {
  late SideMenuStagesModel _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => SideMenuStagesModel());
  }

  @override
  void dispose() {
    _model.maybeDispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: MediaQuery.sizeOf(context).width * 0.16,
      height: MediaQuery.sizeOf(context).height * 1.0,
      decoration: const BoxDecoration(
        color: Color(0xFFF1E2C0),
      ),
      alignment: const AlignmentDirectional(0.0, 0.0),
      child: Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Container(
            width: 180.0,
            height: 90.0,
            decoration: BoxDecoration(
              color: widget.stage == 1
                  ? FlutterFlowTheme.of(context).accent1
                  : FlutterFlowTheme.of(context).accent2,
              boxShadow: const [
                BoxShadow(
                  blurRadius: 4.0,
                  color: Color(0x80000000),
                  offset: Offset(0.0, 5.0),
                )
              ],
              borderRadius: BorderRadius.circular(16.0),
            ),
            child: Column(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Stage 1:',
                  style: FlutterFlowTheme.of(context).headlineSmall.override(
                        fontFamily: 'Outfit',
                        fontWeight: FontWeight.bold,
                      ),
                ),
                Text(
                  'Consultation',
                  style: FlutterFlowTheme.of(context).headlineSmall,
                ),
              ],
            ),
          ),
          Container(
            width: 180.0,
            height: 90.0,
            decoration: BoxDecoration(
              color: widget.stage == 2
                  ? FlutterFlowTheme.of(context).accent1
                  : FlutterFlowTheme.of(context).accent2,
              boxShadow: const [
                BoxShadow(
                  blurRadius: 4.0,
                  color: Color(0x80000000),
                  offset: Offset(0.0, 5.0),
                )
              ],
              borderRadius: BorderRadius.circular(16.0),
            ),
            child: Column(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Stage 2:',
                  style: FlutterFlowTheme.of(context).headlineSmall.override(
                        fontFamily: 'Outfit',
                        fontWeight: FontWeight.bold,
                      ),
                ),
                Text(
                  'Testing',
                  style: FlutterFlowTheme.of(context).headlineSmall,
                ),
              ],
            ),
          ),
          Container(
            width: 180.0,
            height: 90.0,
            decoration: BoxDecoration(
              color: widget.stage == 3
                  ? FlutterFlowTheme.of(context).accent1
                  : FlutterFlowTheme.of(context).accent2,
              boxShadow: const [
                BoxShadow(
                  blurRadius: 4.0,
                  color: Color(0x80000000),
                  offset: Offset(0.0, 5.0),
                )
              ],
              borderRadius: BorderRadius.circular(16.0),
            ),
            child: Column(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Stage 3:',
                  style: FlutterFlowTheme.of(context).headlineSmall.override(
                        fontFamily: 'Outfit',
                        fontWeight: FontWeight.bold,
                      ),
                ),
                Text(
                  'Results',
                  style: FlutterFlowTheme.of(context).headlineSmall,
                ),
              ],
            ),
          ),
          Container(
            width: 180.0,
            height: 90.0,
            decoration: BoxDecoration(
              color: widget.stage == 4
                  ? FlutterFlowTheme.of(context).accent1
                  : FlutterFlowTheme.of(context).accent2,
              boxShadow: const [
                BoxShadow(
                  blurRadius: 4.0,
                  color: Color(0x80000000),
                  offset: Offset(0.0, 5.0),
                )
              ],
              borderRadius: BorderRadius.circular(16.0),
            ),
            child: Column(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Stage 4:',
                  style: FlutterFlowTheme.of(context).headlineSmall.override(
                        fontFamily: 'Outfit',
                        fontWeight: FontWeight.bold,
                      ),
                ),
                Text(
                  'Follow-Up',
                  style: FlutterFlowTheme.of(context).headlineSmall,
                ),
              ],
            ),
          ),
        ].divide(const SizedBox(height: 32.0)).around(const SizedBox(height: 32.0)),
      ),
    );
  }
}
